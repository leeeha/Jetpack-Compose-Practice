package com.gdsc.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdsc.navigation.ui.theme.NavigationTheme

/* https://developer.android.com/guide/navigation
   사용자가 앱 내의 다양한 콘텐츠를 탐색하고, 들어가고, 뒤로 이동하는 등
   여러 화면 간의 전환이 일어날 때, 안내자 역할을 해주는 네비게이션!

   NavHost: 빈 컨테이너. 프래그먼트들이 들어가게 되는 자리
   NavController: NavHost 자리에 프래그먼트들을 들여보내거나 내보내면서, 네비게이션을 관리하는 객체

   해당 화면을 구성하고 있는 composable이 곧 destination이 된다..?
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // navController를 생성해 remember로 기억한다.
                val navController = rememberNavController()

                /*  NavHost
                    navController - the navController for this host
                    startDestination - the route for the start destination
                    modifier - The modifier to be applied to the layout.
                    route - the route for the graph
                    builder - the builder used to construct the graph
                 */
                NavHost(
                    navController = navController,
                    startDestination = "HomeScreen" // 시작할 화면의 루트 전달
                ) {
                    // builder가 함수 형식이기 때문에 이렇게 블록을 밖으로 빼내서 작성할 수 있다.
                    composable("HomeScreen"){
                        Column {
                            Text("HomeScreen")

                            // navController로 여러 화면을 항해한다.
                            Button(onClick = { navController.navigate("MyListScreen")}) {
                                Text("To MyListScreen")
                            }
                        }
                    }
                    composable("MyListScreen"){
                        Text("MyListScreen")
                    }

                    // composable("route"){ composable내용 }으로 destination 화면을 작성하면
                    // 이 화면들 간에 네비게이션(항해)이 가능해진다.
                    // NavBackStackEntry는 인수 전달에 사용됨.
                }
            }
        }
    }
}

