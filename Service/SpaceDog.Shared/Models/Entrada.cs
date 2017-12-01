using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Models
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum Turno
    {
        Matutino,
        Vespertino
    }

    public class Entrada
    {
        public int Id { get; set; }
        public string Folio { get; set; }
        public DateTime Fecha { get; set; }
        public TimeSpan Hora { get; set; }
        public Turno Turno { get; set; }

        public int UsuarioId { get; set; }
        public Usuario Usuario { get; set; }
        public ICollection<Carga> Cargas { get; set; }

        public bool IsDeleted { get; set; }
    }
}
