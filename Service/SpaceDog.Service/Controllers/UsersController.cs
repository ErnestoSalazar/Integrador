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

        public IHttpActionResult Post(Usuario user)
        {
            if (UserService.ValidateEmail(user.Correo) != null)
            {
                return BadRequest("Esta email ya esta registrado");
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var userModel = new Usuario() // can be Dto
            {
                Nombre = user.Nombre,
                Apellido = user.Apellido,
                Rfc = user.Rfc,
                Correo = user.Correo,
                Password = PasswordEncryptService.passwordEncrypt(user.Password),
                Rol = user.Rol
            };

            _usersRepository.Add(userModel);

            user.Id = userModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Users", id = user.Id }),
                user
                ); // 201
        }

        public IHttpActionResult Put(int id, Usuario user)
        {
            if (UserService.ValidateEmail(user.Correo) != null) // if email exists
            {
                if (UserService.OtherUserHaveSameEmail(id, user.Correo))
                {
                    return BadRequest("Email already in use");
                }
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState); // 400
            }

            var _user = new Usuario() // can be Dto
            {
                Id = id,
                Nombre = user.Nombre,
                Apellido = user.Apellido,
                Rfc = user.Rfc,
                Correo = user.Correo,
                Password = PasswordEncryptService.passwordEncrypt(user.Password),
                Rol = user.Rol
            };

            _usersRepository.Update(_user);

            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _usersRepository.Delete(id);
        }

    }
}