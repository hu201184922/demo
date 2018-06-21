package com.fairyland.jdp.framework.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.dao.ResourceDao;
import com.fairyland.jdp.framework.security.dao.RoleDao;
import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.security.domain.JsonResult;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.util.DateUtil;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

@Service(value="userAuthService")
public class UserAuthServiceImpl implements UserAuthService {
	@javax.annotation.Resource
	private RoleDao roleDao;
	@javax.annotation.Resource
	private ResourceDao resourceDao;
	@Autowired
	private UserService userService;
	@Autowired
	private HashService hashService;
	@Autowired
	private TokenService cacheService;
	@Value("${spring.fairyland.auth.expire}")
	private Integer expire;
	private static final String Secret = "dwhncigvh83h!";
	
	@Override
	public Set<Role> getRolesByUserId(Long userId) {
		Set<Role> roles = Sets.newHashSet();

		// 用户直属角色
		List<Role> userRoles = roleDao.findUserRolesByUserId(userId);
		roles.addAll(userRoles);
		// 用户所在组的角色
//		List<Role> groupRoles = roleDao.findGroupRolesByUserId(userId);
//		roles.addAll(groupRoles);
		// 如果有基于组织机构或其它方式的角色分派，均须在此提取
		return roles;
	}

	@Override
	public Set<Resource> getResourcesByUserId(Long userId) {
		Set<Resource> resources = Sets.newHashSet();

		// 用户直属角色
		List<Resource> userRoleResources = resourceDao.findUserRoleResourcesByUserId(userId);
		resources.addAll(userRoleResources);
		// 用户所在组的角色
//		List<Resource> groupRoleResources = resourceDao.findGroupRoleResourcesByUserId(userId);
//		resources.addAll(groupRoleResources);
		// 如果有基于组织机构或其它方式的角色分派，均须在此提取
		return resources;
	}

	@Override
//缓存同步问题涉及面广，暂不使用	@Cacheable(value="SecurityCache", key="'Role_UID:'+#userId.toString()")
	public Set<String> getRoleStringsByUserId(Long userId) {
		Set<Role> roles = getRolesByUserId(userId);
		return Sets.newHashSet(Collections2.transform(roles,
				new Function<Role, String>() {
					@Override
					public String apply(Role input) {
						return input.getCode();
					}
				}));
	}

	@Override
//	@Cacheable(value="SecurityCache", key="'Res_UID:'+#userId.toString()")
	public Set<String> getResourceStringsByUserId(Long userId) {
		Set<Resource> resources = getResourcesByUserId(userId);
		return Sets.newHashSet(Collections2.transform(resources,
				new Function<Resource, String>() {
					@Override
					public String apply(Resource input) {
						return input.getPerString();
					}
				}));
	}

	@Override
	public JsonResult<AuthInfo> userAuth(String username, String password) {
		JsonResult<AuthInfo> result = new JsonResult<AuthInfo>();
		AuthInfo authResult = new AuthInfo();
		result.setValue(authResult);
		result.setSuccess(0);
//		try {
			String userName = username.replaceAll(" ", "");
			User user = userService.findUserByLoginName(userName);
//					logger.debug("............user{" + user.getAccount() + "}............. ");
			if (user != null) {
				Hash passwordHash = hashService.computeHash(password,user.getSalt());
				String p3 = passwordHash.toHex();
				if(p3.equals(user.getPassword())){
					result.setSuccess(1);
					Set<String> resStrings = new HashSet<String>();
					Set<String> perStrings = new HashSet<String>();
					if(!(user.getIsAdmin()!=null&&user.getIsAdmin())){
						List<Resource> resources = resourceDao.findUserRoleResourcesByUserId(user.getId());
						for (Resource resource : resources) {
							perStrings.add(resource.getPerString());
							resStrings.add(resource.getResString());
						}
//						Set<String> set= getResourceStringsByUserId(user.getId());
						authResult.setResourceStrings(resStrings);
						authResult.setPermStrings(perStrings);
//						if (set.size()==0) {
//							throw new AccountException("未授权用户");
//						}
					}
//					Algorithm algorithm = Algorithm.HMAC256(Secret);
//				    String token = JWT.create()
//				    		.withJWTId(user.getId().toString())
//				    		.withExpiresAt(DateUtil.getExpireDate())
//				        .withIssuer("auth0")
//				        .sign(algorithm);
					authResult.setExpireTime(DateUtil.getOffsetTime(expire));
					UUID uuid = UUID.randomUUID();
					Hash accessTokenHash = hashService.computeHash(username+uuid.toString(),user.getSalt());
//					user.setPlainPassword(null);
//					user.setPassword(null);
//					user.setSalt(null);
//					UserInfo userInfo = new UserInfo();
//					BeanUtil.copy(userInfo, user);
//					authResult.setUser(user);
					authResult.setUserId(user.getId());
					if(user.getIsAdmin()==null || !user.getIsAdmin()){
						authResult.setIsAdmin(0);
					}else{
						authResult.setIsAdmin(1);
					}
					authResult.setAccessToken(accessTokenHash.toHex());
					cacheService.setAuthInfo(accessTokenHash.toHex(), authResult);
//					authResult.setResourceStrings(perStrings);
				}else{
					result.setErrorCode("auth_failed");
				}
			} else {
				result.setErrorCode("user_not_found");
//				throw new UnknownAccountException();
			}
		    
//		} catch (UnsupportedEncodingException e){
//			e.printStackTrace();
//		} catch (JWTCreationException e){
//			e.printStackTrace();
//		}
		return result;
	}

	@Override
	public boolean verifyToken(String token) {
//		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
//		try {
//		    Algorithm algorithm = Algorithm.HMAC256("secret");
//		    JWTVerifier verifier = JWT.require(algorithm)
//		        .withIssuer("auth0")
//		        .build(); //Reusable verifier instance
//		    DecodedJWT jwt = verifier.verify(token);
//		    Date now = new Date();
//		    if(now.getTime()>=jwt.getExpiresAt().getTime())
//		    	return false;
//		    return true;
//		} catch (UnsupportedEncodingException exception){
//			exception.printStackTrace();
//		} catch (JWTVerificationException exception){
//			exception.printStackTrace();
//		}
		
		return cacheService.containKey(token);
	}
}
