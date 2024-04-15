### Graalvm
In traditional Java development, source code is first compiled into bytecode, an intermediate language that is machine-independent. This bytecode is then executed by the Java Virtual Machine (JVM), which employs Just-In-Time (JIT) compilation. JIT translates the bytecode into machine-specific code at runtime, optimizing performance as the application runs. However, Ahead-Of-Time (AOT) processing, as employed in tools like GraalVM, takes a different approach. AOT compiles the source code directly into native executables before the application is run, bypassing the bytecode stage. This pre-compilation significantly reduces the application’s startup times and memory consumption compared to JIT compilation, which happens concurrently with application execution.

#### Build-image
```
./mvnw -Dspring.profiles.active=prod spring-boot:build-image
docker run -p 8080:8080 docker.io/library/demo-graalvm:0.0.1-SNAPSHOT
```

#### Pros and Cons of Native Applications
- Native Java applications are a lot smaller than regular JVM based application. By the native compile all unused classes and methods from core Java, your own code and dependencies will be removed from the final build artifact. This usually means, that your application is only 30% the size of a regular Java application.
- Faster startup time
- The memory footprint of a native application is smaller, since there is no JVM running in the background. Only code that is actually used by your application will be loaded into memory.
- This approach significantly reduces startup time and memory footprint compared to the traditional Java Virtual Machine (JVM) runtime.

#### Cons
- The native compile process takes quite a long time compared to a regular Java compilation.
- Out of the box you cannot use reflection to dynamically load classes in your application.
- The application will only run on the operating system and processor architecture you compiled it on.
- If the application is running on a regular JVM, then any AOT generated code is ignored.
- As native-image does not support cross-compilation, you can keep an OS neutral deployment artifact which you convert later to different OS architectures.
- Spring profile support on build time
- GraalVM is not directly aware of dynamic elements of your code and must be told about reflection, resources, serialization, and dynamic proxies.
- There is no lazy class loading, everything shipped in the executables will be loaded in memory on startup.
- There are some limitations around some aspects of Java applications that are not fully supported.
- No thread dump and heap dump support

#### References
- https://medium.com/ekino-france/native-image-with-spring-boot-70f32788528c
- https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html
- https://hilla.dev/blog/ai-chatbot-in-java/deploying-a-spring-boot-app-as-a-graalvm-native-image-with-docker/
- https://java-online-training.de/?p=61
- https://dzone.com/articles/profiling-native-images-in-java
- https://maciejwalkowiak.com/blog/spring-boot-3-native-image-not-a-free-lunch/