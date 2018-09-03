

fun exampleOf(description: String, action: () -> Unit) {
    println("--- Example of: $description ---")
    action()
    println("")
    println("")
    println("")
}