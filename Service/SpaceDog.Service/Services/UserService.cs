using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using SpaceDog.Shared.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Services
{
    public class UserService
    {
        public UserService()
        {

        }

        public Usuario ValidateUser(string correo, string password)
        {
            var _password = PasswordEncryptService.passwordEncrypt(password);
            using (var context = new Context())
            {
                Usuario user = context.Usuarios.FirstOrDefault(u => u.Correo.ToLower() == correo.ToLower() && u.Password == _password);
                return user;
            }
        }


        public static Usuario ValidateEmail(string mail)
        {
            using (var context = new Context())
            {
                return context.Usuarios.FirstOrDefault(u => u.Correo == mail.ToLower());
            }
        }

        public static bool OtherUserHaveSameEmail(int id, string mail)
        {
            using (var context = new Context())
            {
                return context.Usuarios.Any(u => u.Id != id && u.Correo == mail.ToLower());
            }
        }

        public static string GenerateRandomPassword()
        {
            return new Random().Next(1000, 10000).ToString();
        }

    }
}