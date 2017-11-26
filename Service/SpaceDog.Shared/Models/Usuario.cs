using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Models
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum Rol
    {
        Administrador,
        Supervisor,
        Pescador
    }

    public class Usuario
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Apellido { get; set; }
        public string Rfc { get; set; }
        public string Correo { get; set; }
        public string Password { get; set; }
        public Rol Rol { get; set; }

        public bool IsDeleted { get; set; }
    }
}