# Hystrix

## 1. HystrixCommand
Log4j configuration for logging thread name and time

##### 1.1 observe() vs execute() vs queue()
 **observe()**
`command.execute();`
Blocking execution in hystrix thread

**queue()**
`Future<Void> future = command.queue();
future.get();`
NonBlocking execution until we call get() on future

**toObservable() or observe()**
NonBlocking - asynchronous
Facade on HystrixCommand


##### 1.2 HystrixObservableCommand - observe(), toObservable()
`observe.subscribe(System.out::println);`
NonBlocking execution - notify subscriber once new element coming in
*Show that cannot execute() or queue() command*
*Add one more element to the SimpleObservableCommand() to show that ObservableCommand can return more than one element*
*Show which thread is used to run observable. observe() ignores hystrix threads and subscribeOn(). only toObservable() works*
`observe.subscribeOn(Schedulers.io()).subscribe(System.out::println);`
*FROM hystrix github: If you already have a non-blocking Observable, you don't need another thread*


##### 1.3 Reusing command
*Show that hystrix command cannot be reused what it was executed*
`        try {
             command.execute();
             command.execute();
         } catch (HystrixRuntimeException e) {
             logger.error(e);
         }
         `
## 2. Command configuration
Run Main and show whats logged etc

*andCommandKey()*
Sets the name of the command executed - by default is the class name

*andThreadPoolKey()*
Default ThreadPoolName is taken from HystrixCommandGroupKey
If we logically want these commands grouped together but want them isolated differently then we would use HystrixThreadPoolKey to give each of them a different thread-pool.

*andThreadPoolPropertiesDefaults - coreSize()*
Discussed in chapter 4 - settings for thread pool and command execution strategy

*CircuitBreakerSleepWindowInMilliseconds()*
The time in milliseconds after a HystrixCircuitBreaker trips open that it should wait before trying requests again.

## 3. Fallback
Show exceptions when no fallback available and default response for command with fallback
Will back to fallback when we discuss exceptions in hystrix

## 4. Hystrix thread pools
##### 4.1 own thread pools (coreSize, maximumSize with withAllowMaximumSizeToDivergeFromCoreSize, queueSize) rejections
#####4.2 isolation strategy - semaphore vs thread
THREAD: Execute the HystrixCommand.run() method on a separate thread and restrict concurrent executions using the thread-pool size.
SEMAPHORE: Execute the HystrixCommand.run() method on the calling thread and restrict concurrent executions using the semaphore permit count.

######4.2.1 - thread
######4.2.1 - semaphore (show what will happen when provide different command key like using counter
#####4.3 ignoring rxjava observeOn() and subscribeOn() - executing observable in hystrixCommand, observeOn() execuites post hystrix in other thread

## 5. Hystrix error handling
##### 5.1 HystrixRuntimeException (failure types)
        - command exception,
        - timeout (add more than 1 sec),
        - shortcircuit (uncomment withCircuitBreakerForceOpen)
        - rejectedThreadExecution (ToManyCommandsMain)
##### 5.2 HystrixBadRequestException (fallback)
##### 5.3 unwrapHystrixException() - show code from UMG ?

## 6. Request caching - use HystrixRequestContext, show what happened when close context and reinit

## 7. Request batching/collapsing
        - configure withTimerDelayInMilliseconds (delay), and withMaxRequestsInBatch, without HystrixRequestContext no exception!!

## 8. Issue with MDC

## 9. Hystrix dashboard (open Web project, run Hystrix Dasbhoard from CMD, us ab.exe)

