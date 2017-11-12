using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
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


        public Carga ToModel()
        {
            return new Carga()
            {
                Id = Id,
                Cantidad = Cantidad,
                Especie = Especie,
                Talla = Talla,
                Temperatura = Temperatura,
                Condicion = Condicion,
                Barco = Barco
            };
        }

    }
}