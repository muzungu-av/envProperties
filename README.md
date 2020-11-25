[![Build Status](https://travis-ci.org/muzungu-av/envProperties.svg?branch=main)](https://travis-ci.org/muzungu-av/envProperties)
[![codecov](https://codecov.io/gh/muzungu-av/envProperties/branch/main/graph/badge.svg?token=ZEZHR9ZCB8)](https://codecov.io/gh/muzungu-av/envProperties)

# envProperties
Wrap over System.getenv command

### How to use it
Use feature of the Jitpack project.
Declare the repository:

    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>

Declare dependency:

    <dependency>
        <groupId>com.github.muzungu-av</groupId>
        <artifactId>envProperties</artifactId>
        <version>${LATEST}</version>
    </dependency>


If there are environment variables in the system that are known for sure, 
then use the Pojo-class:

    class Props {
        @Env
        private String JAVA_HOME;
        @Env
        private String PATH;
        String getJAVA_HOME() {
            return JAVA_HOME;
        }
        String getPATH() {
            return PATH;
        }
    }

Now do it:

    Props p = EnvProperties.readEnv(Props.class);

Variables can be obtained like this:

    p.getJAVA_HOME()
    p.getPATH()

### Where can you use it

For example, to store application **configuration** on different machines.
On the developer's machine, you need to set up your environment, and on the test machine other.