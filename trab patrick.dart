import 'package:flutter/material.dart';

void main() {
  runApp(const MeuApp());
}

class MeuApp extends StatelessWidget {
  const MeuApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'App de Cadastro',

      theme: ThemeData(
        scaffoldBackgroundColor: Colors.grey[100],
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.blue,
          foregroundColor: Colors.white,
        ),
      ),
      home: const TelaCadastro(),
      debugShowCheckedModeBanner: false,
    );
  }
}


class TelaCadastro extends StatefulWidget {
  const TelaCadastro({super.key});

  @override
  State<TelaCadastro> createState() => _TelaCadastroState();
}

class _TelaCadastroState extends State<TelaCadastro> {
  // Q1 / Q5: Controllers para gerenciar e manter os dados
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _idadeController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();

  String? _sexoSelecionado;
  bool _termosAceitos = false;

  @override
  void dispose() {
    _nomeController.dispose();
    _idadeController.dispose();
    _emailController.dispose();
    super.dispose();
  }


  void _validarECadastrar() {
    final nome = _nomeController.text.trim();
    final idadeTexto = _idadeController.text.trim();
    final email = _emailController.text.trim();


    if (nome.isEmpty) {
      _mostrarErro("O nome não pode ser vazio.");
      return;
    }


    if (idadeTexto.isEmpty) {
      _mostrarErro("A idade não pode ser vazia.");
      return;
    }
    try {
      final idade = int.parse(idadeTexto);
      if (idade < 18) {
        _mostrarErro("A idade deve ser maior ou igual a 18 anos.");
        return;
      }
    } catch (e) {
      _mostrarErro("Idade inválida. Digite apenas números.");
      return;
    }


    if (email.isEmpty || !email.contains("@") || !email.contains(".")) {
      _mostrarErro("Por favor, insira um e-mail válido.");
      return;
    }


    if (_sexoSelecionado == null) {
      _mostrarErro("Por favor, selecione um sexo.");
      return;
    }


    if (!_termosAceitos) {
      _mostrarErro("Você deve aceitar os termos de uso para continuar.");
      return;
    }


    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => TelaConfirmacao(
          nome: nome,
          idade: idadeTexto,
          email: email,
          sexo: _sexoSelecionado!,
          termosAceitos: _termosAceitos,
        ),
      ),
    );
  }

  void _mostrarErro(String mensagem) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(mensagem),
        backgroundColor: Colors.redAccent,
        behavior: SnackBarBehavior.floating,
      ),
    );
  }


  InputDecoration _estiloCampo(String label, String hint) {
    return InputDecoration(
      labelText: label,
      hintText: hint,
      border: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12.0),
      ),
      contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
      filled: true,
      fillColor: Colors.white,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Cadastro de Usuário"), // Q1
        centerTitle: true,
      ),

      body: Center(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [

              const Text(
                "Preencha os campos abaixo",
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 32),


              TextField(
                controller: _nomeController,
                decoration: _estiloCampo("Nome", "Ex: Maria Silva"),
                textInputAction: TextInputAction.next, // Q6: Navegação com Enter
              ),
              const SizedBox(height: 16),


              TextField(
                controller: _idadeController,
                decoration: _estiloCampo("Idade", "Ex: 25"),
                keyboardType: TextInputType.number, // Q6: Teclado numérico
                textInputAction: TextInputAction.next,
              ),
              const SizedBox(height: 16),


              TextField(
                controller: _emailController,
                decoration: _estiloCampo("E-mail", "Ex: maria@email.com"),
                keyboardType: TextInputType.emailAddress, // Q6: Teclado de email
                textInputAction: TextInputAction.done,
              ),
              const SizedBox(height: 16),


              DropdownButtonFormField<String>(
                decoration: _estiloCampo("Sexo", "Selecione"),
                value: _sexoSelecionado,
                items: ["Masculino", "Feminino", "Outro"]
                    .map((String valor) => DropdownMenuItem<String>(
                  value: valor,
                  child: Text(valor),
                ))
                    .toList(),
                onChanged: (String? novoValor) {
                  setState(() {
                    _sexoSelecionado = novoValor;
                  });
                },
              ),
              const SizedBox(height: 16),


              Row(
                children: [
                  Checkbox(
                    value: _termosAceitos,
                    activeColor: Colors.blue,
                    onChanged: (bool? valor) {
                      setState(() {
                        _termosAceitos = valor ?? false;
                      });
                    },
                  ),
                  const Expanded(
                    child: Text(
                      "Aceito os termos de uso e políticas de privacidade.",
                      style: TextStyle(fontSize: 16),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 32),


              SizedBox(
                height: 50,
                child: ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.blue,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12),
                    ),
                  ),
                  onPressed: _validarECadastrar,
                  child: const Text(
                    "Cadastrar",
                    style: TextStyle(fontSize: 18, color: Colors.white),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}


class TelaConfirmacao extends StatelessWidget {
  final String nome;
  final String idade;
  final String email;
  final String sexo;
  final bool termosAceitos;

  const TelaConfirmacao({
    super.key,
    required this.nome,
    required this.idade,
    required this.email,
    required this.sexo,
    required this.termosAceitos,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Confirmação"), // Q4
        centerTitle: true,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              const Icon(Icons.check_circle, color: Colors.green, size: 80),
              const SizedBox(height: 24),
              const Text(
                "Dados Cadastrados:",
                style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 24),


              _construirLinhaDado("Nome:", nome),
              _construirLinhaDado("Idade:", "$idade anos"),
              _construirLinhaDado("E-mail:", email),
              _construirLinhaDado("Sexo:", sexo),
              _construirLinhaDado("Termos aceitos:", termosAceitos ? "Sim" : "Não"),

              const SizedBox(height: 40),


              Row(
                children: [
                  Expanded(
                    child: OutlinedButton(
                      style: OutlinedButton.styleFrom(
                        padding: const EdgeInsets.symmetric(vertical: 16),
                        side: const BorderSide(color: Colors.blue),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),

                      onPressed: () => Navigator.pop(context),
                      child: const Text("Editar", style: TextStyle(fontSize: 16)),
                    ),
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.blue,
                        padding: const EdgeInsets.symmetric(vertical: 16),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      onPressed: () => Navigator.pop(context),
                      child: const Text("Voltar", style: TextStyle(fontSize: 16, color: Colors.white)),
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _construirLinhaDado(String label, String valor) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            label,
            style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
          const SizedBox(width: 8),
          Expanded(
            child: Text(
              valor,
              style: const TextStyle(fontSize: 18),
            ),
          ),
        ],
      ),
    );
  }
}