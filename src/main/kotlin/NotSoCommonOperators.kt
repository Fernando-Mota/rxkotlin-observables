import io.reactivex.Observable
import java.util.concurrent.Callable
import java.util.concurrent.Executors

fun main(args: Array<String>) {

    exampleOf("defer") {

        var flag = true

        val observable = Observable.defer {

            flag = !flag

            if (flag) {
                Observable.just("Item 1", "Item 2", "Item 3")
            } else {
                Observable.just("Item 4", "Item 5", "Item 6")
            }
        }

        observable
                .subscribe { value ->
                    println(value)
                }.dispose()

        println("")
        println("Segunda inscrição")
        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("never") {
        val observable = Observable.never<Unit>()
        observable
                .subscribe({
                    println("Never next")
                }, {
                    println("Never error")
                }, {
                    println("Never complete!")
                })
    }

    exampleOf("range") {
        val observable = Observable.range(17, 6)

        observable
                .subscribe { value ->
                    println(value)
                }.dispose()
    }

    exampleOf("empty") {
        val observable = Observable.empty<Void>()

        observable.subscribe(
                { value ->
                    println(value)
                },
                { error ->
                    println(error.stackTrace)
                },
                {
                    println("empty completed")
                }).dispose()
    }
}