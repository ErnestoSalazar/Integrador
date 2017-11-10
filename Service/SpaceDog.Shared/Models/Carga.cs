using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Models
{
    public enum Especie { Pez1, Pez2, Pez3 }
    public enum Talla { s, m, x, xl }
    public enum Condicion { Mala, Regular,Buena }

    public class Carga
    {
        public int Id { get; set; }
        public Double Cantidad { get; set; }
        public Especie Especie { get; set; }
        public Talla Talla { get; set; }
        public Double Temperatura { get; set; }
        public Condicion Condicion { get; set; }

        public int BarcoId { get; set; }
        public Barco Barco { get; set; }
    }
}
