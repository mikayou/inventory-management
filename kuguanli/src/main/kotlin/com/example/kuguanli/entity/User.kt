package com.example.kuguanli.entity

data class User(
    var id: Int,
    var uuid: String,
    var name: String,
    var password: String,
    var role: Int
) {
    override fun toString(): String {
        return "User(id=$id, uuid=$uuid, name='$name', password='$password', role=$role)"
    }

    fun isSame(user: User): Boolean {
        return user.name == this.name && user.password == this.password
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

}

