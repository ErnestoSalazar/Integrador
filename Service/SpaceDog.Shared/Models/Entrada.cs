using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Models
{
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
        public DateTime Hora { get; set; }
        public Turno Turno { get; set; }
        public Usuario Usuario { get; set; }
        
    }
}
