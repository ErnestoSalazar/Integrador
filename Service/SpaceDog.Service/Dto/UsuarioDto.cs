using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{
    public class UsuarioDto
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public string Rfc { get; set; }
        public string Correo { get; set; }
        public string Password { get; set; }
        public Rol Rol { get; set; }



        public Usuario ToModel()
        {
            return new Usuario()
            {
                Id = Id,
                Nombre = Nombre,
                Apellido = Apellido,
                Rfc = Rfc,
                Correo = Correo,
                Password = Password,
                Rol = Rol
            };
        }
    }
}