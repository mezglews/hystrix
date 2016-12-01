1. HystrixCommand
    1.1 observe() vs execute() vs queue()
    1.2 HystrixObservableCommand - observe(), toObservable()
    1.3 Reusing command

2. Command configuration

3. Fallback

4. Hystrix thread pools
    4.1 own thread pools (coreSize, maximumSize with withAllowMaximumSizeToDivergeFromCoreSize, queueSize)
    4.2 isolation strategy - semaphore vs thread
    4.3 exceeded thread pool, exceeded semaphore size
    4.4 ignoring rxjava observeOn() and subscribeOn()

5. Hystrix error handling
    - HystrixRuntimeException (failure types)
    - HystrixBadRequestException (fallback)
    - unwrapHystrixException()
3. Hystrix dashboard
8. Request caching
6. Request batching
7. Request collapsing
