using SpaceDog.Service.Services;
using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using SpaceDog.Shared.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Http;

namespace SpaceDog.Service.Controllers
{

    public class PasswordRecoverController : ApiController
    {
        private UsuariosRepository _usersRepository = null;
        public PasswordRecoverController(UsuariosRepository usersRepository)
        {
            _usersRepository = usersRepository;
        }
        
        [Route("login/recover")]
        public IHttpActionResult Post(EmailService email)
        {
            Usuario user = UserService.ValidateEmail(email.mailTo);
            if (user != null)
            {
                var password = UserService.GenerateRandomPassword();
                user.Password = PasswordEncryptService.passwordEncrypt(password);
                _usersRepository.Update(user);
                EmailService.SendPasswordRecoveryMail(email.mailTo, $"Se ha generado una nueva contraseña: {password} (recuerde cambiarla al ingresar!)");
                return Ok();
            }
            return NotFound();
        }

    }
}