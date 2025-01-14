package Application.controllers;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.Profile;

import Application.model.RankingDTOList;
import Application.model.SubjectDTO;
import Application.services.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Controlador de Perfis")
@RequestMapping({ "v1/profiles" })
public class ProfileController {

	private ProfileService profileService;

	public ProfileController(ProfileService profileService) {

		this.profileService = profileService;
	}

	@ApiOperation(value = "Cadastra um novo perfil de uma disciplina")
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Iterable<Profile>> create(@RequestBody Iterable<Profile> dis) {
		return new ResponseEntity<>(this.profileService.create(dis), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna um perfil de uma disciplina")
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Profile> get(@PathVariable long id, ServletRequest request) {
		return new ResponseEntity<>(this.profileService.getProfile(id, request), HttpStatus.OK);
	}

	@ApiOperation(value = "Dar like a um perfil de uma disciplina")
	@PostMapping(value = "/like/{profile}")
	@ResponseBody
	public ResponseEntity<Profile> createLike(@PathVariable Long profile, ServletRequest request) {
		return new ResponseEntity<>(this.profileService.toLike(request, profile), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retorna uma lista de perfis a partir de uma substring")
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<SubjectDTO>> getBySubstring(@RequestParam(name = "substring") String substring) {
		return new ResponseEntity<>(this.profileService.findBySubstring(substring.toUpperCase()), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma lista de perfis ordenados por número de comentarios e likes")
	@GetMapping(value = "/ranking")
	@ResponseBody
	public ResponseEntity<RankingDTOList> ranking() {
		return new ResponseEntity<>(this.profileService.rankingTop10(), HttpStatus.OK);
	}
}
