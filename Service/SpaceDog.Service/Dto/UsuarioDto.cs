using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{
    public class UsuarioDto
    {
        public int Id { get; set; }
        [Required (ErrorMessage ="Nombre requerido")]
        public string Nombre { get; set; }
        [Required]
        public string Apellido { get; set; }
        [Required]
        public string Rfc { get; set; }
        [Required]
        public string Correo { get; set; }
        [Required]
        public string Password { get; set; }
        public string PasswordConfirmation { get; set; }
        [Required]
        public Rol? Rol { get; set; }



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
                Rol = Rol.Value
            };
        }
    }
}