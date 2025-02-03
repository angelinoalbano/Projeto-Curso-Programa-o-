package main;

public class clasmoda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		package main;
		import java.math.BigDecimal;
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		import java.util.Scanner;

		public class conectar {

		static String URL = "jdbc:mysql://localhost:3306/senac";
		static String USER = "root";
		static String PASSWORD = "123456";

		public static Connection conexao_com_banco() {
		try {
		return DriverManager.getConnection(URL, USER, PASSWORD); }
		catch (SQLException e){
		System.err.println("ERRO, QUEBROU TUDO :c " + e.getMessage());
		return null; }}

		public static void inserirDados() throws SQLException {

		Scanner scanner = new Scanner(System.in);

		        System.out.println("Digite o nome do produto:");
		        String nome = scanner.nextLine();

		        System.out.println("Digite o preço do produto:");
		        BigDecimal preco = new BigDecimal(scanner.nextLine());

		        System.out.println("Digite a quantidade do produto:");
		        int quantidade = Integer.parseInt(scanner.nextLine());

		String sql = "INSERT INTO produtos(nome, preco, quantidade) values (?,?,?);";
		try(Connection realizar_conexao = conexao_com_banco();
		PreparedStatement  cursor = realizar_conexao.prepareStatement(sql)){

		cursor.setString(1, nome);
		cursor.setBigDecimal(2, preco);
		cursor.setInt(3, quantidade);
		cursor.executeUpdate();
		System.out.println("Inserido com sucesso "); }}


		public static void consultarDados() throws SQLException {
		String sql = "Select * from produtos";

		try(Connection realizar_conexao = conexao_com_banco();
		Statement cursor = realizar_conexao.createStatement();
		ResultSet resultado_consulta = cursor.executeQuery(sql)
		){

		while (resultado_consulta.next()) {
		               int id = resultado_consulta.getInt("id");
		               String nome = resultado_consulta.getString("nome");
		               double preco = resultado_consulta.getDouble("preco");
		               int quantidade = resultado_consulta.getInt("quantidade");

		               System.out.printf("ID: %d, Nome: %s, Preço: %.2f, Quantidade: %d%n",
		                                 id, nome, preco, quantidade);
		           }
		//System.out.println(resultado_consulta);
		}
		}

		public static void main(String[] args) throws SQLException {
		/*Connection realizar_conexao = conexao_com_banco();

		if (realizar_conexao != null) {
		System.out.println("Conexão OK =D "); }
		else {
		System.out.println("Falha na Conexão =( ");
		}*/

		// inserirDados();
		consultarDados();
		}}
	}

}

