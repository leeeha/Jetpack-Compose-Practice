package com.gdsc.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

/* https://developer.android.com/guide/navigation
   사용자가 앱 내의 다양한 콘텐츠를 탐색하고, 들어가고, 뒤로 이동하는 등
   여러 화면 간의 전환이 일어날 때, 안내자 역할을 해주는 네비게이션!

   NavHost: 빈 컨테이너. 프래그먼트들이 들어가게 되는 자리
   NavController: NavHost 자리에 프래그먼트들을 들여보내거나 내보내면서, 네비게이션을 관리하는 객체
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "HomeScreen" // 시작할 화면의 루트 전달
            ) {
                // builder가 함수 형식이기 때문에 이렇게 블록을 밖으로 빼내서 작성할 수 있다.
                composable("HomeScreen"){
                    HomeScreen(navController = navController)
                }
                composable(
                    route = "MyListScreen/{argText}", // 인자 전달 받기
                    arguments = listOf(
                        navArgument("argText"){
                            type = NavType.StringType
                        },
                    ),
                ){ backStackEntry ->
                    // 전달된 인자는 backstackEntry의 arguments에 Bundle 형태로 저장되므로,
                    // getString에 key를 전달하여 그에 대응하는 value(인자)를 꺼낼 수 있다.
                    Text("Passed argument: ${backStackEntry.arguments?.getString("argText")}")
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var inputText by remember { // 입력된 텍스트를 기억해뒀다가
            mutableStateOf("")
        }

        Text("HomeScreen")
        TextField(
            value = inputText,
            onValueChange = { string ->
                inputText = string
        })

        // 버튼을 클릭했을 때, inputText를 MyListScreen에 인자로 전달한다!
        Button(onClick = { navController.navigate("MyListScreen/${inputText}")}) {
            Text("To MyListScreen")
        }
    }
}