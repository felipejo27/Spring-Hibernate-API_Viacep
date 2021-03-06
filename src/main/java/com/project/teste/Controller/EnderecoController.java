package com.project.teste.Controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.teste.Exception.RequisicaoFailedException;
import com.project.teste.Exception.RequisicaoNaoEncontradaException;
import com.project.teste.Interface.iEnderecoService;
import com.project.teste.Model.Endereco;
import com.project.teste.Model.Usuario;
import com.project.teste.Repositorio.EnderecoRepositorio;
import com.project.teste.Repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private iEnderecoService cepService;

	@Autowired
	private EnderecoRepositorio repositorio;

	@Autowired
	private UsuarioRepositorio repositorioUser;
	
	/*
	 * Cadastrar um Endereço
	 */

	@SuppressWarnings({})
	@PutMapping("/cadastrar")
	public ResponseEntity<Endereco> cadastrar(@RequestHeader("cep") String cep,
			@RequestHeader("complemento") String complemento, @RequestHeader("numero") String numero,
			@RequestHeader("CPF") String CPF) {

		Endereco endereco = cepService.buscarPeloCep(cep);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		String id = CPF;
		Optional<Usuario> usuario = repositorioUser.findByCPF(id);

		try {
			if (usuario != null) {
				if (usuario.isPresent()) {
					endereco.setUsuario(usuario.get());
					endereco.usuario.setCPF(CPF);
					repositorio.save(endereco);
					return new ResponseEntity<>(endereco, HttpStatus.CREATED);
				}
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * Buscar um Endereço via API ViaCep
	 */

	@GetMapping("/getCep")
	public ResponseEntity<Endereco> getCep(@RequestHeader("cep") String cep,
			@RequestHeader("complemento") String complemento, @RequestHeader("numero") String numero) {

		Endereco endereco = cepService.buscarPeloCep(cep);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);

		return endereco != null ? ResponseEntity.ok().body(endereco) : ResponseEntity.notFound().build();
	}

	/*
	 * Buscar todos os Endereços de um Usuário pelo CPF
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/buscar")
	public ResponseEntity buscar(@RequestHeader("CPF") String CPF) {
		try {
			List<Endereco> list = repositorio.findAllByUsuarioCPF(CPF);
			if (!list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
			return new ResponseEntity(new RequisicaoNaoEncontradaException("Usuario", "CPF", CPF),
					HttpStatus.NOT_FOUND);

		} catch (Exception ex) {

			return new ResponseEntity(new RequisicaoFailedException("usuario", "CPF", CPF), HttpStatus.BAD_REQUEST);
		}

	}
}
