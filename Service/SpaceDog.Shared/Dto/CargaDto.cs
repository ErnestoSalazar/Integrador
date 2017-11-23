using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Dto
{
    public class CargaDto
    {
        public int Id { get; set; }
        public Double Cantidad { get; set; }
        public Especie Especie { get; set; }
        public Talla Talla { get; set; }
        public Double Temperatura { get; set; }
        public Condicion Condicion { get; set; }

        public int BarcoId { get; set; }
        public Barco Barco { get; set; }

        public int? EntradaId { get; set; }
        public Entrada Entrada { get; set; }
    }
}
