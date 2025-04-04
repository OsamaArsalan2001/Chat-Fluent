package com.example.chat_fluent

sealed interface Destination {
    open val route:String


}
object HomePage: Destination{
    override val route:String = "Home"
}
object SignupPage: Destination{
    override val route: String = "Signup"
}