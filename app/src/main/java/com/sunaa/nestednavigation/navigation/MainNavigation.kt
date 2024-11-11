package com.sunaa.nestednavigation.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sunaa.nestednavigation.auth.subauth.AuthOneScreen
import com.sunaa.nestednavigation.auth.subauth.AuthTwoScreen
import com.sunaa.nestednavigation.home.subhome.HomeOneScreen
import com.sunaa.nestednavigation.home.subhome.HomeTwoScreen
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Parcelize
@Serializable
data class User(
    val name : String,
    val age : Int
) : Parcelable

val UserType = object : NavType<User>(false) {
    override fun get(bundle: Bundle, key: String): User? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, User::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): User {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: User) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: User): String {
        return Json.encodeToString(value)
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SubGraph.Auth) {

        navigation<SubGraph.Auth>(startDestination = Dest.AuthOneScreen) {
            composable<Dest.AuthOneScreen> {
                AuthOneScreen {
                    navController.navigate(Dest.AuthSecondScreen("Sunil", 23))
                }
            }
            composable<Dest.AuthSecondScreen> {
                val dashArgs = it.toRoute<Dest.AuthSecondScreen>()
                AuthTwoScreen(dashArgs) {
                    navController.navigate(Dest.HomeOneScreen)
                }
            }
        }
        navigation<SubGraph.Home>(startDestination = Dest.HomeOneScreen) {
            composable<Dest.HomeOneScreen> {
                HomeOneScreen {
                    navController.navigate(Dest.HomeSecondScreen(User(name = "Sunil", age = 23)))
                }
            }
            composable<Dest.HomeSecondScreen> (
                typeMap = mapOf(typeOf<User>() to UserType)
            ){
                val user = it.toRoute<Dest.HomeSecondScreen>()
                HomeTwoScreen(user) {
                    navController.navigate(Dest.HomeOneScreen)
                }
            }
        }

    }
}


