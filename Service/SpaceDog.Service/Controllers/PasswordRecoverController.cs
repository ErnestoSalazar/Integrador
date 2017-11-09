﻿using SpaceDog.Service.Services;
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

    public class PasswordRecoverController : ApiController
    {
        private UsersRepository _usersRepository = null;
        public PasswordRecoverController(UsersRepository usersRepository)
        {
            _usersRepository = usersRepository;
        }


        [Route("login/recover")]
        public IHttpActionResult Post(EmailService email)
        {
            User user = UserService.ValidateEmail(email.mailTo);
            if (user != null)
            {
                var password = new Random().Next(1000, 10000).ToString();
                user.Password = PasswordEncryptService.passwordEncrypt(password);
                _usersRepository.Update(user);
                EmailService.SendPasswordRecoveryMail(email.mailTo, $"Se ha generado una nueva contraseña: {password} (recuerde cambiarla al ingresar!)");
                return Ok();
            }
            return NotFound();
        }

    }
}