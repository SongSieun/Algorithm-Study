private var location = Pair(0, 0)
private var mapSize = Pair(0, 0)
private val dice: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0)
private val map = mutableListOf<MutableList<Int>>()

fun main() {
    val condition = readLine()
    val conditionSplit = condition?.split(" ") ?: return
    mapSize = Pair(conditionSplit[0].toInt(), conditionSplit[1].toInt()) // 지도의 크기
    location = Pair(conditionSplit[2].toInt(), conditionSplit[3].toInt()) // 주사위를 놓은 곳의 좌표 x, y
    val k = conditionSplit[4].toInt() // 명령의 갯수

    for (i in 0 until mapSize.first) {
        getLine()?.let { map.add(it) }
    }

    val command = readLine()
    val commandSplit = command?.split(" ") ?: return

    commandSplit
        .forEach { command ->
            if(!checkValidLocation(command)) return@forEach
            when (command.toInt()) {
                1 -> moveUpEast()
                2 -> moveUpWest()
                3 -> moveUpNorth()
                4 -> moveUpSouth()
            }
            checkValue()
        }

}

private fun getLine(): MutableList<Int>? {
    val line = readLine()
    return line?.split(" ")?.map { it.toInt() }?.toMutableList()
}

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

// 주사위 윗면 0, 동 1, 서 2, 남 3, 북 4, 밑면 5
private fun moveUpNorth() {
    var temp: Int = dice[0]

    dice.apply {
        set(0, dice[3])
        set(3, dice[5])
        set(5, dice[4])
        set(4, temp)
    }
    println("${dice.first()}")
}


private fun moveUpSouth() {
    var temp: Int = dice[0]

    dice.apply {
        set(0, dice[4])
        set(4, dice[5])
        set(5, dice[3])
        set(3, temp)
    }
    println("${dice.first()}")
}


private fun moveUpWest() {
    var temp: Int = dice[0]

    dice.apply {
        set(0, dice[2])
        set(2, dice[5])
        set(5, dice[1])
        set(1, temp)
    }
    println("${dice.first()}")
}


private fun moveUpEast() {
    var temp: Int = dice[0]

    dice.apply {
        set(0, dice[1])
        set(1, dice[5])
        set(5, dice[2])
        set(2, temp)
    }
    println("${dice.first()}")
}