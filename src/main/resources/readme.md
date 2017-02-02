# Hystrix

## 1. HystrixCommand
##### 1.1 observe() vs execute() vs queue()
##### 1.2 HystrixObservableCommand - observe(), toObservable()
##### 1.3 Reusing command
##### 1.4 Hystrix observeOn() and subsequent heavy operations on stream

## 2. Command configuration

## 3. Fallback

## 4. Hystrix thread pools
##### 4.1 own thread pools (coreSize, maximumSize with withAllowMaximumSizeToDivergeFromCoreSize, queueSize) rejections
#####4.2 isolation strategy - semaphore vs thread
######4.2.1 - thread
######4.2.2 - semaphore (show what will happen when provide different command key like using counter
#####4.3 ignoring rxjava observeOn() and subscribeOn() - executing observable in hystrixCommand, observeOn() execuites post hystrix in other thread

## 5. Hystrix error handling
##### 5.1 HystrixRuntimeException (failure types)
        - command exception,
        - timeout (add more than 1 sec),
        - shortcircuit (uncomment withCircuitBreakerForceOpen)
        - rejectedThreadExecution (ToManyCommandsMain)
##### 5.2 HystrixBadRequestException
##### 5.3 unwrapHystrixException()

## 6. Request caching - use HystrixRequestContext, show what happened when close context and reinit

## 7. Request batching/collapsing
        - configure withTimerDelayInMilliseconds (delay), and withMaxRequestsInBatch, without HystrixRequestContext no exception!!

## 8. Issue with MDC

## 9. Hystrix dashboard (open Web project, run Hystrix Dasbhoard from CMD, us ab.exe)

??HystrixRequestLog

