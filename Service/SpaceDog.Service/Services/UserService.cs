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

        public Usuario ValidateUser(string name, string password)
        {
            var _password = PasswordEncryptService.passwordEncrypt(password);
            using (var context = new Context())
            {
                Usuario user = context.Users.FirstOrDefault(u => u.Nombre == name && u.Password == _password);
                return user;
            }
        }

        /*
         * return User if exists
         */
        public static Usuario ValidateEmail(string mail)
        {
            using (var context = new Context())
            {
                return context.Users.FirstOrDefault(u => u.Correo == mail);
            }
        }

        public static bool OtherUserHaveSameEmail(int id, string mail)
        {
            using (var context = new Context())
            {
                return context.Users.Any(u => u.Id != id && u.Correo == mail);
            }
        }

    }
}