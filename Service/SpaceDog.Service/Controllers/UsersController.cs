using SpaceDog.Service.Dto;
using SpaceDog.Service.Services;
using SpaceDog.Shared;
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
            var users = _usersRepository.GetList();
            if(users.Count <= 0)
            {
                return NotFound();
            }
            return Ok(users);
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
                return BadRequest(Strings.ENTIDAD_INVALIDA);
            }

            var password = UserService.GenerateRandomPassword();
            usuarioDto.Password = PasswordEncryptService.passwordEncrypt(password);

            var userModel = usuarioDto.ToModel();
            _usersRepository.Add(userModel);

            EmailService.SendPasswordForNewUser(userModel.Correo, $"Bienvenido {userModel.Nombre} {userModel.Apellido}, aquí tienes tu contraseña de acceso: {password} (No olvides cambiarlo)");

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
                    return BadRequest("Correo ya en uso");
                }
            }


            usuario.Nombre      = (usuarioDto.Nombre != null    && usuarioDto.Nombre.Length > 0)        ? usuarioDto.Nombre     :  usuario.Nombre;
            usuario.Apellido    = (usuarioDto.Apellido != null  && usuarioDto.Apellido.Length > 0)      ? usuarioDto.Apellido   : usuario.Apellido;
            usuario.Rfc         = (usuarioDto.Rfc != null       && usuarioDto.Rfc.Length > 0)           ? usuarioDto.Rfc        : usuario.Rfc;
            usuario.Correo      = (usuarioDto.Correo != null    && usuarioDto.Correo.Length> 0)         ? usuarioDto.Correo     : usuario.Correo;
            usuario.Password    = (usuarioDto.Password != null  && usuarioDto.Password.Length > 0 )     ? PasswordEncryptService.passwordEncrypt(usuarioDto.Password) : usuario.Password;
            usuario.Rol         = (usuarioDto.Rol != null       && usuarioDto.Rol.ToString().Length > 0)? usuarioDto.Rol.Value  : usuario.Rol;
            

            _usersRepository.Update(usuario);

            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public IHttpActionResult Delete(int id)
        {
            var users = _usersRepository.GetList();

            if(users.Count > 0 && users.Count < 2)
            {
                return Content(System.Net.HttpStatusCode.BadRequest, Strings.ELIMINACION_INVALIDA_ULTIMO_USUARIO);
            }
            _usersRepository.DeleteD(id);

            return Content(System.Net.HttpStatusCode.NoContent, "usuario eliminado");
        }


        public IHttpActionResult GetUsersByRol(string rol)
        {
            var users = _usersRepository.GetUsersByRol(rol);
            if(users.Count <= 0)
            {
                return NotFound();
            }
            return Ok(users);
        }

        public IHttpActionResult GetUsersByName(string nombre,string apellido)
        {
            var users = _usersRepository.GetUsuariosByName(nombre,apellido);
            if(users == null)
            {
                return NotFound();
            }
            if(users.Count <= 0)
            {
                return NotFound();
            }
            return Ok(users);
        }

    }
}