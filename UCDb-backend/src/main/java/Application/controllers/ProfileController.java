package Application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.Comment;
import Application.model.Profile;
import Application.services.ProfileService;

@RestController
@RequestMapping({ "v1/profiles" })
public class ProfileController {

	private ProfileService disciplineProfileService;

	public ProfileController(ProfileService profileService) {

		this.disciplineProfileService = profileService;
	}

	@PostMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Profile> create(@PathVariable long id, @RequestBody Profile profile) {
		return new ResponseEntity<Profile>(this.disciplineProfileService.create(id, profile), HttpStatus.CREATED);

	}

	@GetMapping(value = "/{id}/{email}")
	@ResponseBody
	public ResponseEntity<Profile> get(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<Profile>(this.disciplineProfileService.getProfile(id, email), HttpStatus.OK);

	}

	@PostMapping(value = "/like/{profile}/{email}")
	@ResponseBody
	public ResponseEntity<Profile> createLike(@PathVariable String email, @PathVariable Long profile) {

		return new ResponseEntity<Profile>(this.disciplineProfileService.toLike(email, profile), HttpStatus.CREATED);

	}

	@PostMapping(value = "/comment/{profile}/{email}")
	@ResponseBody
	public ResponseEntity<Profile> usuarioComentou(@PathVariable long profile, @PathVariable String email,
			@RequestBody Comment comment) {

		return new ResponseEntity<Profile>(this.disciplineProfileService.toComment(profile, email, comment),
				HttpStatus.OK);
	}
}
