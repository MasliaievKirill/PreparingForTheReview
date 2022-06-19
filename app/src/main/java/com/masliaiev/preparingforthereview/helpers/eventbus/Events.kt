package com.masliaiev.preparingforthereview.helpers.eventbus

sealed class Events{

    object AddItemToDatabase: Events()
    class ShowEmployeeInfo(val id: Int): Events()
}
