import io.reactivex.Observable
import java.util.concurrent.Callable
import java.util.concurrent.Executors

fun main(args: Array<String>) {

    exampleOf("just - one element") {
        val observable = Observable.just("Item 1")
        observable
                .subscribe { value ->
                    print(value)
                }.dispose()
    }

    exampleOf("just - more than one element") {
        val observable = Observable.just("Item 1", "Item 2", "Item 3")
        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("from - array") {
        val observable = Observable.fromArray("Item 1", "Item 2", "Item 3")
        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("from - iterable") {
        val observable = Observable.fromIterable(mutableListOf("Item 1", "Item 2", "Item 3"))
        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("from - callable") {
        val task = Callable {
            "Retorno do callable!"
        }
        val observable = Observable.fromCallable(task)
        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("from - future") {
        val executor = Executors.newFixedThreadPool(1)
        val task = Callable {
            "Retorno do future!"
        }
        val future = executor.submit(task)
        val observable = Observable.fromFuture(future)
        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("create") {
        val observable = Observable.create<String> { observer ->
            observer.onNext("Item 1")
            observer.onNext("Item 2")
            observer.onNext("Item 3")
            observer.onComplete()
        }

        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("subscribe") {
        val observable = Observable.create<String> { observer ->
            observer.onNext("Item 1")
            observer.onNext("Item 2")
            //observer.onError(Exception("Erro entre os elementos da sequência!"))
            observer.onNext("Item 3")
            observer.onComplete()
        }

        observable
                .subscribe ( { value ->
                    println(value)
                }, { error ->
                    println(error.message)
                }, {
                    println("Completou!")
                }).dispose()
    }
}