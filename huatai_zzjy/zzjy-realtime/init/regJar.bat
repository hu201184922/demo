set localdir=%~dp0  
call mvn install:install-file -Dfile=ojdbc7-12.1.0.2.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
call mvn install:install-file -Dfile=jdp-mybatis-1.0.jar -DgroupId=com.fairyland.jdp -DartifactId=jdp-mybatis -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=ImpalaJDBC41.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=ImpalaJDBC41 -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=ql.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=ql -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=TCLIServiceClient.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=TCLIServiceClient -Dversion=1.0 -Dpackaging=jar