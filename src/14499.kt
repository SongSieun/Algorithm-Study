fun main() {
    val condition = readLine()
    val split = condition?.split(" ") ?: return
    val n = split[0] // 지도의 세로크기
    val m = split[1] // 지도의 가로크기
    val x = split[2] // 주사위를 놓은 곳의 좌표 x
    val y = split[3] // 주사위를 놓은 곳의 좌표 y
    val k = split[4] // 명령의 갯수

    val line1 = repeat(n.length) { getLine() }
}

fun getLine(): List<String>? {
    val line = readLine()
    return line?.split(" ")
}