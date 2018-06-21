package com.fairyland.jdp.framework.schedule.quartz;

/**
 * 被Spring的Quartz JobDetailBean定时执行的Job类, 支持持久化到数据库实现Quartz集群.
 * 
 * 因为需要被持久化, 不能有用XXService等不能被持久化的成员变量,
 * 只能在每次调度时从QuartzJobBean注入的applicationContext中动态取出.
 * 
 * @author calvin
 */
public class QuartzClusterableJob //extends QuartzJobBean 
{

//	private static Logger logger = LoggerFactory
//			.getLogger(QuartzClusterableJob.class.getName() + ".quartz cluster job");
//
//	private ApplicationContext applicationContext;
//
//	/**
//	 * 从SchedulerFactoryBean注入的applicationContext.
//	 */
//	public void setApplicationContext(ApplicationContext applicationContext) {
//		this.applicationContext = applicationContext;
//	}
//
//	/**
//	 * 定时打印当前用户数到日志.
//	 */
//	@Override
//	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
//		UserService accountService = applicationContext.getBean(UserService.class);
//		Map config = (Map) applicationContext.getBean("timerJobConfig");
//
//		long userCount = accountService.getAllUser().size();
//		String nodeName = (String) config.get("nodeName");
//
//		logger.info("There are {} user in database, on node {}.", userCount, nodeName);
//	}
}
