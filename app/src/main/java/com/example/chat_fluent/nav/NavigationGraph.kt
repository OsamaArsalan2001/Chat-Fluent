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

object StartPage: Destination{
    override val route: String = "StartPage"
}
object ChatScreen: Destination {
    override val route: String = "chat"
}
object OpenAIChatScreen : Destination {
    //override val route: String = "openai_chat"
    const val TOPIC_ARG = "topic"
    override val route: String = "chat/{$TOPIC_ARG}" // ✅ Match NavHost route
    fun createRoute(topic: String) = "chat/$topic" // Remove nullable param

   // fun createRoute(topic: String? = null) = if (topic != null) "$route/$topic" else route
}
object ChatScreenWithTopic: Destination {
    override val route: String = "ChatM"
    const val TOPIC_ARG = "topic"

    fun createRoute(topic: String) = "chat/$topic"
}
object FeedbackScreen: Destination {
    override val route: String = "feedback"
}


/*
object ProfileScreen: Destination{
    override val route: String = "Profile"
}
object FeedbackScreen: Destination{
    override val route: String = "Feedback"
}
object PracticeScreen: Destination{
    override val route: String = "Practice"
}*/
