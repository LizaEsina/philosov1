data class Philosopher(val name: String)
enum class ChopstickStatus {
    FREE, OCCUPIED
}

class Chopstick {
    var status = ChopstickStatus.FREE
}
class DiningTable(val numPhilosophers: Int) {
    private val philosophers = mutableListOf<Philosopher>()
    private val chopsticks = mutableListOf<Chopstick>()

    init {
        for (i in 1..numPhilosophers) {
            philosophers.add(Philosopher("Философы $i"))
            chopsticks.add(Chopstick())
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
                println("${philosopher.name} обедает")
            } else {
                println("${philosopher.name} думает")
            }

            currentIndex = (currentIndex + 1) % numPhilosophers
        }
    }
}
fun main() {
    print("Введите кол-во философов: ")
    var numPhilosophers = readLine()?.toIntOrNull()
    while (numPhilosophers == null || numPhilosophers <= 0) {
        print("Введите кол-во философов: ")
        numPhilosophers = readLine()?.toIntOrNull()
        if (numPhilosophers == null || numPhilosophers <= 0) {
            println("Не правильное число")
        }
    }
    val diningTable = DiningTable(numPhilosophers)
    diningTable.startDining()
}

