import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

fun main(args: Array<String>) {

    exampleOf("single") {

        val single: Single<String> = Single.just("Single com just")
        single
                .subscribe({ value ->
                    println(value)
                }).dispose()

        val singleTwo = Single.create<String> { single ->
            single.onSuccess("Este é o success create!")
        }

        println("")
        println("Single com create")
        singleTwo
                .subscribe({ value ->
                    println(value)
                }).dispose()
    }

    exampleOf("completable") {
        val completable = Completable.create { completable ->

            completable.onComplete()

        }

        completable
                .subscribe( {
                    print("Compretou tudo!")
                })
    }

    exampleOf("maybe") {

        val maybe = Maybe.create<String> { emitter ->
            //SUPER REGRA DE NEGOCIO
            val random = Random()
            val value = random.nextInt(3)

            when (value) {
                0 -> {
                    emitter.onSuccess("Evento de sucesso!")
                }
                1 -> {
                    emitter.onComplete()
                }
                else -> {
                    emitter.onError(Exception("Evento de erro!"))
                }
            }
        }

        maybe
                .subscribe(
                        { value ->
                            println("Maybe terminando com sucesso - $value")
                        },
                        { error ->
                            println("ERROR: ${error.message}")
                        },
                        {
                            println("Maybe completano!")
                        })

    }
}