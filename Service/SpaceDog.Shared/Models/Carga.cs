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
    public enum Especie { Macarela, Japonesa, Monterrey, Rayadillo, Bocona, Anchoveta, Crinuda }
    [JsonConverter(typeof(StringEnumConverter))]
    public enum Talla { S, M, L, XL }
    [JsonConverter(typeof(StringEnumConverter))]
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

        public int? EntradaId { get; set; }
        
    }
}
