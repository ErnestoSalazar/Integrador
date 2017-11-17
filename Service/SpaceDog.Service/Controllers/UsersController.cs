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
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var password = UserService.GenerateRandomPassword();
            usuarioDto.Password = PasswordEncryptService.passwordEncrypt(password);

            var userModel = usuarioDto.ToModel();
            _usersRepository.Add(userModel);

            EmailService.SendPasswordForNewUser(userModel.Correo, $"Bienvenido nuevo usuario, aquí tienes tu contraseña de acceso: {password}");

            usuarioDto.Id = userModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Users", id = usuarioDto.Id }),
                usuarioDto
                ); // 201
        }

        public IHttpActionResult Put(int id, UsuarioDto usuarioDto)
        {
            var usuario = _usersRepository.Get(id);
            if(usuario == null)
            {
                return BadRequest("Usuario inexistente");
            }
            if (UserService.ValidateEmail(usuarioDto.Correo) != null) // if email exists
            {
                if (UserService.OtherUserHaveSameEmail(id, usuarioDto.Correo)) // if other user have same email
                {
                    return BadRequest("Email already in use");
                }
            }


            usuario.Nombre      = (usuarioDto.Nombre != null)   ? usuarioDto.Nombre     :  usuario.Nombre;
            usuario.Apellido    = (usuarioDto.Apellido != null) ? usuarioDto.Apellido   : usuario.Apellido;
            usuario.Rfc         = (usuarioDto.Rfc != null)      ? usuarioDto.Rfc        : usuario.Rfc;
            usuario.Correo      = (usuarioDto.Correo != null)   ? usuarioDto.Correo     : usuario.Correo;
            usuario.Password    = (usuarioDto.Password != null) ? PasswordEncryptService.passwordEncrypt(usuarioDto.Password) : usuario.Password;
            usuario.Rol         = (usuarioDto.Rol != null)      ? usuarioDto.Rol.Value  : usuario.Rol;
            

            _usersRepository.Update(usuario);

            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _usersRepository.Delete(id);
        }

    }
}