package com.sunaa.nestednavigation.navigation

import kotlinx.serialization.Serializable

sealed class SubGraph {
    @Serializable
    data object Auth : SubGraph()

    @Serializable
    data object Home : SubGraph()

    // we annotate it with Serializable in order to use Auth and Home Object as a Destination route in navigation

}

// Now Each SubGraph contains few Screens let's create those here

sealed class Dest {
    @Serializable
    data object AuthOneScreen : Dest()

    @Serializable
    data class AuthSecondScreen(
        val name : String,
        val age : Int
    ) : Dest()

    @Serializable
    data object HomeOneScreen : Dest()

    @Serializable
    data class HomeSecondScreen(
        val user : User
    ) : Dest()
}