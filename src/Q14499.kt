private var location = Pair(0, 0)
private var mapSize = Pair(0, 0)
private val dice: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0)
private val map = mutableListOf<MutableList<Int>>()
private var temp = 0

fun main() {
    val condition = readLine()?.split(" ") ?: return
    mapSize = Pair(condition[0].toInt(), condition[1].toInt()) // 지도의 크기
    location = Pair(condition[2].toInt(), condition[3].toInt()) // 주사위를 놓은 곳의 좌표 x, y
    val k = condition[4].toInt() // 명령의 갯수

    for (i in 0 until mapSize.first) getLine()?.let { map.add(it) }

    val command = readLine()?.split(" ") ?: return

    command.forEach { command ->
        if (!checkValidLocation(command)) return@forEach
        temp = dice[0]
        when (command.toInt()) {
            1 -> moveEast()
            2 -> moveWest()
            3 -> moveNorth()
            4 -> moveSouth()
        }
        println("${dice.first()}")
        checkValue()
    }

}

private fun getLine(): MutableList<Int>? = readLine()?.split(" ")?.map { it.toInt() }?.toMutableList()

private fun checkValidLocation(command: String): Boolean {
    var (x, y) = location
    val (m, n) = mapSize
    when (command.toInt()) {
        1 -> x += 1 // 동쪽으로
        2 -> x -= 1
        3 -> y -= 1
        4 -> y += 1 // 남쪽으로
    }

    if (0 <= x && x <= n - 1 && 0 <= y && y <= m - 1) {
        location = Pair(x, y)
        return true
    } else {
        return false
    }
}

private fun checkValue() {
    val (m, n) = location
    val curLocation = map[n][m]

    if (curLocation == 0) {
        map[n][m] = dice[5]
    } else {
        dice[5] = map[n][m]
        map[n][m] = 0
    }
}

private fun moveNorth() = dice.apply {
    set(0, dice[3])
    set(3, dice[5])
    set(5, dice[4])
    set(4, temp)
}

private fun moveSouth() = dice.apply {
    set(0, dice[4])
    set(4, dice[5])
    set(5, dice[3])
    set(3, temp)
}


private fun moveWest() = dice.apply {
    set(0, dice[2])
    set(2, dice[5])
    set(5, dice[1])
    set(1, temp)
}


private fun moveEast() = dice.apply {
    set(0, dice[1])
    set(1, dice[5])
    set(5, dice[2])
    set(2, temp)
}