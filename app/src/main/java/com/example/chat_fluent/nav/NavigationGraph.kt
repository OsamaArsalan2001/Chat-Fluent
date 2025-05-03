package com.example.chat_fluent

sealed interface Destination {
     val route:String


}
object HomePage: Destination{
    override val route:String = "Home"
}
object SignupPage: Destination{
    override val route: String = "Signup"
}

object LoginPage: Destination{
    override  val route: String = "Login"
}
object MainScreen: Destination{
    override val route: String = "Main"
}
/*
object HomeScreen: Destination{
    override val route: String = "HomeScreen"
}
object ProfileScreen: Destination{
    override val route: String = "Profile"
}
object FeedbackScreen: Destination{
    override val route: String = "Feedback"
}
object PracticeScreen: Destination{
    override val route: String = "Practice"
}*/
