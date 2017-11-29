using SpaceDog.Service.Dto;
using SpaceDog.Shared;
using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace SpaceDog.Service.Controllers
{
    [Authorize]
    public class BarcosController : ApiController
    {

        private BarcosRepository _barcosRepository = null;
        private UsuariosRepository _usuariosRepository = null;

        public BarcosController(BarcosRepository barcosRepository, UsuariosRepository usuariosRepository)
        {
            _barcosRepository = barcosRepository;
            _usuariosRepository = usuariosRepository;
        }


        public IHttpActionResult Get()
        {
            var barcos = _barcosRepository.GetList();
            if(barcos.Count <= 0)
            {
                return NotFound();
            }
            return Ok(barcos);
        }

        public IHttpActionResult Get(int id)
        {
            var barco = _barcosRepository.Get(id);
            if (barco == null)
            {
                return NotFound();
            }
            return Ok(barco);
        }

        public IHttpActionResult Post(BarcoDto barcoDto)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(Strings.ENTIDAD_INVALIDA);
            }
            var barcoModel = barcoDto.ToModel();

            _barcosRepository.Add(barcoModel);

            barcoDto.Id = barcoModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Barcos", id = barcoDto.Id }),
                barcoDto
                );
        }

        public IHttpActionResult Put(int id, BarcoDto barcoDto)
        {

            var _barco = barcoDto.ToModel();
            _barco.Id = id;
            _barco.UsuarioId = barcoDto.UsuarioId;

            _barcosRepository.Update(_barco);

            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _barcosRepository.DeleteD(id);
        }

        public IHttpActionResult GetBarcosByName(string nombre)
        {
            var barcos = _barcosRepository.GetBarcosByName(nombre);

            if(barcos == null)
            {
                return NotFound();
            }
            if(barcos.Count <= 0)
            {
                return NotFound();
            }
            return Ok(barcos);

        }


    }
}