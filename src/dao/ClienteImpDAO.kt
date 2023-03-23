package dao

class ClienteImpDAO : IClienteDAO {
    // ...
    override fun aniadirCliente(nombre: String, apellido: String, email: String, telefono: String) {
        val sql = "insert into clientes (nombre, apellido, email, telefono) values (?, ?, ?, ?);"
        val conn = ConexionBD()
        conn.conectar()
        val ps = conn.getPreparedStatement(sql)
        ps?.setString(1, nombre)
        ps?.setString(2, apellido)
        ps?.setString(3, email)
        ps?.setString(4, telefono)
        ps?.executeUpdate()
        conn.desconectar()
    }

    override fun eliminarCliente(id:Int){
        val sql = "delete from clientes where id = ?;"
        val conn = ConexionBD()
        conn.conectar()
        val ps = conn.getPreparedStatement(sql)
        ps?.setInt(1, id)
        ps?.executeUpdate()
        conn.desconectar()
    }

    override fun verListaCliente(): List<Cliente>{
        val sql = "select * from clientes;"
        val clientes = ArrayList<Cliente>()
        val conn = ConexionBD()
        conn.conectar()
        val ps = conn.getPreparedStatement(sql)
        val rs = ps?.executeQuery()
        while (rs?.next() == true) {
            val id = rs.getInt("id")
            val nombre = rs.getString("nombre")
            val apellido = rs.getString("apellido")
            val email = rs.getString("email")
            val telefono = rs.getString("telefono")
            clientes.add(Cliente(id,nombre, apellido, email, telefono))
        }
        rs?.close()
        ps?.close()
        conn.desconectar()
        return clientes
    }

    override fun buscarCliente(id: Int): Cliente? {
        val sql = "select * from clientes where id = ?;"
        val conn = ConexionBD()
        conn.conectar()
        val ps = conn.getPreparedStatement(sql)
        ps?.setInt(1, id)
        val rs = ps?.executeQuery()
        var cliente: Cliente? = null
        if (rs?.next() == true) {
            val nombre = rs.getString("nombre")
            val apellido = rs.getString("apellido")
            val email = rs.getString("email")
            val telefono = rs.getString("telefono")
            cliente = Cliente(id, nombre, apellido, email, telefono)
        }
        rs?.close()
        ps?.close()
        conn.desconectar()
        return cliente
    }
}
