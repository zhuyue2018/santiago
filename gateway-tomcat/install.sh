# ls -lrt /etc/alternatives/java
export PROJECT_NAME=gateway
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_11
export PATH=$JAVA_HOME/bin:$PATH
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib

export M2_HOME=/usr/local/maven/apache-maven-3.3.9
export CLASSPATH=$CLASSPATH:$M2_HOME/lib
export PATH=$PATH:$M2_HOME/bin

source /etc/profile
mvn -version
mvn clean install -Dmaven.test.skip=true -Dmaven.compile.fork=true -T 1C
rm -rf /usr/local/docker/scan/${PROJECT_NAME}*
mkdir /usr/local/docker/scan/${PROJECT_NAME}
cp ./target/${PROJECT_NAME}-1.0.0-SNAPSHOT.jar /usr/local/docker/scan/${PROJECT_NAME}/
cd /usr/local/docker/scan/${PROJECT_NAME}
cp ../docker-compose.yml ./
sed -i 's/8080/8090/g' docker-compose.yml
sed -i 's/projectname/gateway/g' docker-compose.yml
docker-compose down
docker-compose up -d