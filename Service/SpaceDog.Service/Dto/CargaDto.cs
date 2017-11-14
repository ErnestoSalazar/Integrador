using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{
    public class CargaDto
    {
        public int Id { get; set; }
        [Required]
        public Double Cantidad { get; set; }
        [Required]
        public Especie? Especie { get; set; }
        [Required]
        public Talla? Talla { get; set; }
        [Required]
        public Double Temperatura { get; set; }
        [Required]
        public Condicion? Condicion { get; set; }

        public int BarcoId { get; set; }


        public Carga ToModel()
        {
            return new Carga()
            {
                Id = Id,
                Cantidad = Cantidad,
                Especie = Especie.Value,
                Talla = Talla.Value,
                Temperatura = Temperatura,
                Condicion = Condicion.Value,
                BarcoId = BarcoId
            };
        }

    }
}