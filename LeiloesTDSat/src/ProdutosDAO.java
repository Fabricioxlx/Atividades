/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = ("Insert into produtos (nome, valor, status) values (?, ?, ?)");
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            
            JOptionPane.showMessageDialog(null, "Produto Adicionado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Produto não Adicionado. Tente novamente!");
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = ("Select * from produtos");
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
            return listagem;
        } catch (SQLException e) {
            return null;
        }
    }
    
    
    
        
}

