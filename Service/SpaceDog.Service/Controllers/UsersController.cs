using SpaceDog.Service.Dto;
using SpaceDog.Service.Services;
using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using SpaceDog.Shared.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace SpaceDog.Service.Controllers
{

    public class UsersController : ApiController
    {
        private UsuariosRepository _usersRepository = null;


        public UsersController(UsuariosRepository usersRepository)
        {
            _usersRepository = usersRepository;
        }


        public IHttpActionResult Get()
        {
            return Ok(_usersRepository.GetList());
        }


        public IHttpActionResult Get(int id)
        {
            var user = _usersRepository.Get(id);
            if (user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }

        public IHttpActionResult Post(UsuarioDto usuarioDto)
        {
            if (UserService.ValidateEmail(usuarioDto.Correo) != null)
            {
                return BadRequest("Esta email ya esta registrado");
            }
            if(usuarioDto.Password != usuarioDto.PasswordConfirmation)
            {
                return BadRequest("Las contraseñas no coinciden");
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var userModel = usuarioDto.ToModel();

            _usersRepository.Add(userModel);

            usuarioDto.Id = userModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Users", id = usuarioDto.Id }),
                usuarioDto
                ); // 201
        }

        public IHttpActionResult Put(int id, UsuarioDto usuarioDto)
        {
            if (UserService.ValidateEmail(usuarioDto.Correo) != null) // if email exists
            {
                if (UserService.OtherUserHaveSameEmail(id, usuarioDto.Correo)) // if other user have same email
                {
                    return BadRequest("Email already in use");
                }
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState); // 400
            }

            var usuario = usuarioDto.ToModel();
            usuario.Id = id;

            _usersRepository.Update(usuario);

            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _usersRepository.Delete(id);
        }

    }
}