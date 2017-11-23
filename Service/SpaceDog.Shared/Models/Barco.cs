using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Models
{
    public class Barco
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Descripcion { get; set; }

        public int UsuarioId { get; set; }
        public Usuario Usuario { get; set; }

        public bool IsDeleted { get; set; }
    }
}
