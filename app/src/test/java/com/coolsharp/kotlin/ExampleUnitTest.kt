package com.coolsharp.kotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private val MAX_SIZE = 7

    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    var n = 7 // 행과 열의 수
    var group_id = 0 // 단지의 번호로 첫번째 단지부터 1로 시작

    val groups = IntArray(MAX_SIZE * MAX_SIZE) // 각 단지별 집의 수

    val map = arrayOf(
        intArrayOf(0, 1, 1, 0, 1, 0, 0),
        intArrayOf(0, 1, 1, 0, 1, 0, 1),
        intArrayOf(1, 1, 1, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 0, 1, 1, 1),
        intArrayOf(0, 1, 0, 0, 0, 0, 0),
        intArrayOf(0, 1, 1, 1, 1, 1, 0),
        intArrayOf(0, 1, 1, 1, 0, 0, 0)
    )

    var visited = arrayOf<Array<Boolean>>()

    @Test
    fun testDfs() {
        for (i in 0..MAX_SIZE) {
            var array = arrayOf<Boolean>()
            for (j in 0..MAX_SIZE) {
                array += false
            }
            visited += array
        }
        // 전체 지도 탐색
//        = > = = > = =
//        = = = = = = =
//        = = = = = = =
//        = = = = = = =
//        = > = = = = =
//        = = = = = = =
//        = = = = = = =
        for (i in 0 until n) {
            for (j in 0 until n) {
                // 집이면서 방문하지 않았다면 -> 방문
                if (map[i][j] == 1 && !visited[i][j]) {

                    // 해당 지역에 단지 id를 부여하고
                    group_id++

                    print(">[%s][%s]".format(i, j))

                    // 탐색
                    dfs_recursion(i, j)
                    //dfs_stack(i, j);
                    //bfs(i, j);
                } else {
                    print("=[%s][%s]".format(i, j))
                }
                println()
            }
        }

        println("그룹 ID " + group_id)
        for (i in 1..group_id) {
            println(groups[i])
        }
    }

    fun dfs_recursion(x: Int, y: Int) {

        // 함수에 들어왔으면 -> 방문으로 표시
        visited[x][y] = true
        // 해당 단지의 집의 수를 증가시킴
        groups[group_id]++

        // 해당 위치의 주변을 확인
        for (i in 0..3) {
            val nx: Int = x + dx[i]
            val ny: Int = y + dy[i]

            // 지도를 벗어나지 않고
            if (nx in 0 until n && 0 <= ny && ny < n) {
                // 집이면서 방문하지 않았다면 -> 방문
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    dfs_recursion(nx, ny)
                }
            }
        }
    }

//    Array<Int> is an Integer[] under the hood, while IntArray is an int[].

    @Test
    fun question() {
        val array = intArrayOf(1, 1, 2, 2, 2, 2, 3, 8, 1, 1)
        solutionOddEven(array)
        println(array.joinToString())
    }

    fun solution(numbers: IntArray) {
        var r = 0
        for (i in numbers.indices) {
            if (numbers[i] % 2 == 1) {
                if (i > r) {
                    val t = numbers[r]
                    numbers[r] = numbers[i]
                    numbers[i] = t
                }
                r++
            }
        }
    }

    fun solutionOddEven(numbers: IntArray) {
        var r = 0
        var c = 1 // 홀수가 먼저면 1 짝수가 면저면 0
        for (i in numbers.indices) {
            if (r < numbers.size - 1) {
                if (numbers[i] % 2 == c) {
                    if (i > r) {
                        val temp = numbers[r]
                        numbers[r] = numbers[i]
                        numbers[i] = temp
                    }

                    if (numbers[r] % 2 != numbers[r + 1] % 2) {
                        r += 2
                    } else {
                        c = c xor 1 // 0 or 1 토글
                        r++
                    }
                }
            }
        }
    }


}
