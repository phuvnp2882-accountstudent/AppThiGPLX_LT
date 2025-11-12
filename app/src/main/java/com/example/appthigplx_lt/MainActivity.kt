package com.example.appthigplx_lt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = MyDbHelper(this)
        db.createDefaultLyThuyet()
        db.createDefaultBienBao()

        val path = this.getDatabasePath("DB_OnThiGPLX").absolutePath
        Log.d("DB_PATH", "Đường dẫn database: $path")

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { Home(navController) }
        composable("traCuuBienBao") { TraCuuBienBao(navController) }
        composable("onTheoChuDe") { OnTheoChuDe(navController) }
        composable("onLyThuyet/{chuDe}") { backStackEntry ->
            val chuDe = backStackEntry.arguments?.getString("chuDe") ?: ""
            OnLyThuyet(navController, chuDe)
        }
        composable("meoOnThi") { MeoOnThi(navController) }
        composable("chonBoDe") { ChonBoDe(navController) }
        composable("quyCheThi/{boDe}") { backStackEntry ->
            val boDe = backStackEntry.arguments?.getString("boDe") ?: "Đề 1"
            QuyCheThi(navController, boDe)
        }
        composable("thiSatHach/{boDe}") { backStackEntry ->
            val boDe = backStackEntry.arguments?.getString("boDe") ?: "1"
            ThiSatHach(navController, boDe)
        }
        composable("ketQua/{tongDiem}/{boDe}/{coSaiDiemLiet}") { backStackEntry ->
            val tongDiem = backStackEntry.arguments?.getString("tongDiem")?.toInt() ?: 0
            val boDe = backStackEntry.arguments?.getString("boDe") ?: ""
            val coSaiDiemLiet = backStackEntry.arguments?.getString("coSaiDiemLiet")?.toBoolean() ?: false

            KetQua(navController, tongDiem, boDe, coSaiDiemLiet)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    val fakeNav = rememberNavController()
    Home(fakeNav)
}
