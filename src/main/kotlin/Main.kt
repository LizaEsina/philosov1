data class Philosopher(val name: String)
enum class ChopstickStatus {
    FREE, OCCUPIED
}

class Chopstick(val id: Int) {
    var status = ChopstickStatus.FREE
}
class DiningTable(val numPhilosophers: Int) {
    private val philosophers = mutableListOf<Philosopher>()
    private val chopsticks = mutableListOf<Chopstick>()

    init {
        for (i in 1..numPhilosophers) {
            philosophers.add(Philosopher("Philosopher $i"))
            chopsticks.add(Chopstick(i))
        }
    }
    fun startDining() {
        val startIndex = (0..numPhilosophers - 1).random()
        var currentIndex = startIndex

        for (i in 1..numPhilosophers) {
            val philosopher = philosophers[currentIndex]
            val leftChopstick = chopsticks[currentIndex]
            val rightChopstick = chopsticks[(currentIndex + 1) % numPhilosophers]

            if (leftChopstick.status == ChopstickStatus.FREE && rightChopstick.status == ChopstickStatus.FREE) {
                leftChopstick.status = ChopstickStatus.OCCUPIED
                rightChopstick.status = ChopstickStatus.OCCUPIED
                println("${philosopher.name} is dining")
            } else {
                println("${philosopher.name} is contemplating")
            }

            currentIndex = (currentIndex + 1) % numPhilosophers
        }
    }
}
fun main() {
    print("Enter the number of philosophers: ")
    var numPhilosophers = readLine()?.toIntOrNull()
    while (numPhilosophers == null || numPhilosophers <= 0) {
        print("Enter the number of philosophers: ")
        numPhilosophers = readLine()?.toIntOrNull()
        if (numPhilosophers == null || numPhilosophers <= 0) {
            println("Invalid number of philosophers")
        }
    }
    val diningTable = DiningTable(numPhilosophers)
    diningTable.startDining()
}

